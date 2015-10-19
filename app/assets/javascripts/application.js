// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or vendor/assets/javascripts of plugins, if any, can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file.
//
// Read Sprockets README (https://github.com/sstephenson/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require dataTables/jquery.dataTables
//= require dataTables/bootstrap/3/jquery.dataTables.bootstrap
//= require parsley
//= require turbolinks
//= require bootstrap-sprockets
//= require bootstrap-datepicker
//= require bootstrap-datepicker-rails
//= require websocket_rails/main
//= require highstock
//= require highstock/highcharts-more
//= require_tree .


$( document ).ready(function() {
    window.setTimeout(function() {
        $(".alert").fadeTo(500, 0).slideUp(500, function(){
            $(this).fadeOut();
        });
    }, 2000);
})

function loadRemoteModal(url, title) {
    BootstrapDialog.show({
        title: title,
        size: BootstrapDialog.SIZE_WIDE,
        message: $('<div></div>').load(url)
    });
}

function closeBootstrapDialog() {
    BootstrapDialog.closeAll();
}