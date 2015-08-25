/**
 * Created by Guilherme on 23/08/2015.
 */

$(document).ready(function(){
    $('.navbar-nav li').click(function() {
        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
    });
});