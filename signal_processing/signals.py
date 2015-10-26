import bitalino,time,json,requests
import numpy as np
import scipy.signal
import scipy.ndimage
import sys

def detect_pulse(
		ecg,	# The raw ECG signal
		rate,	# Sampling rate in HZ
		# Window size in seconds to use for
		ransac_window_size=5.0,
		# Low frequency of the band pass filter
		lowfreq=5.0,
		# High frequency of the band pass filter
		highfreq=15.0,
		):

	ransac_window_size = int(ransac_window_size*rate)

	lowpass = scipy.signal.butter(1, highfreq/(rate/2.0), 'low')
	highpass = scipy.signal.butter(1, lowfreq/(rate/2.0), 'high')
	# TODO: Could use an actual bandpass filter
	ecg_low = scipy.signal.filtfilt(*lowpass, x=ecg)
	ecg_band = scipy.signal.filtfilt(*highpass, x=ecg_low)

	# Square (=signal power) of the first difference of the signal
	decg = np.diff(ecg_band)
	decg_power = decg**2

	# Robust threshold and normalizator estimation
	thresholds = []
	max_powers = []
	for i in range(len(decg_power)/ransac_window_size):
		sample = slice(i*ransac_window_size, (i+1)*ransac_window_size)
		d = decg_power[sample]
		thresholds.append(0.5*np.std(d))
		max_powers.append(np.max(d))

	threshold = 0.5*np.std(decg_power)
	threshold = np.median(thresholds)
	max_power = np.median(max_powers)
	decg_power[decg_power < threshold] = 0

	decg_power /= max_power
	decg_power[decg_power > 1.0] = 1.0
	square_decg_power = decg_power**2

	shannon_energy = -square_decg_power*np.log(square_decg_power)
	shannon_energy[~np.isfinite(shannon_energy)] = 0.0

	mean_window_len = int(rate*0.125+1)
	lp_energy = np.convolve(shannon_energy, [1.0/mean_window_len]*mean_window_len, mode='same')
	#lp_energy = scipy.signal.filtfilt(*lowpass2, x=shannon_energy)

	lp_energy = scipy.ndimage.gaussian_filter1d(lp_energy, rate/8.0)
	lp_energy_diff = np.diff(lp_energy)

	zero_crossings = (lp_energy_diff[:-1] > 0) & (lp_energy_diff[1:] < 0)
	zero_crossings = np.flatnonzero(zero_crossings)
	zero_crossings -= 1
	return zero_crossings


sensors = {5:'emg', 6:'eda', 7:'ecg', 8:'accel', 9:'light'}

postURL = "http://vitalo.remotelab.club/readings"

device = bitalino.BITalino()
device.open(macAddress="/dev/tty.bitalino-DevB", SamplingRate=1000)
print 'opened device'
device.start()
print 'started acquisiton'
reads = 0
while True:
    print "reading", reads
    data = device.read(100)
    total = 0
    for x in range(0, 99):
        total = total + data[8][x]
    avg = int(total/100)
    print "Movement: " + str(avg)

    json = {"serial_number":"3434","value":avg,"sensor":"movement"}
    r = requests.post(postURL, json, headers={'content-type': 'application/json'})

    total = 0
    for x in range(0, 99):
        total = total + data[7][x]
    avg = int(total/100)/10
    print "Pulse: " + str(avg)

    json = {"serial_number":"3434","value":avg,"sensor":"pulse"}
    r = requests.post(postURL, json, headers={'content-type': 'application/json'})
    reads+=1
