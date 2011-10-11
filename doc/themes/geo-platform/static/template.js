/* Copyright (C) 2007 - 2011 YOOtheme GmbH, YOOtheme Proprietary Use License (http://www.yootheme.com/license) */

jQuery(function($){

	// Accordion menu
	$('.menu-sidebar').accordionMenu({ mode:'slide' });

	// Dropdown menu
	$('#menu').dropdownMenu({ mode: 'slide', dropdownSelector: 'div.dropdown'});

	// Smoothscroller
	$('a[href="#page"]').smoothScroller({ duration: 500 });

	// Fix Browser Rounding
	$('.grid-block').matchWidth('.grid-h');

	// Match height of div tags
	var matchHeight = function() {
		$('#top-a .grid-h').matchHeight('.deepest');
		$('#top-b .grid-h').matchHeight('.deepest');
		$('#bottom-a .grid-h').matchHeight('.deepest');
		$('#bottom-b .grid-h').matchHeight('.deepest');
		$('#innertop .grid-h').matchHeight('.deepest');
		$('#innerbottom .grid-h').matchHeight('.deepest');
		$('#maininner, #sidebar-a, #sidebar-b').matchHeight();
	};

	matchHeight();

	$(window).bind('load', matchHeight);
	
});