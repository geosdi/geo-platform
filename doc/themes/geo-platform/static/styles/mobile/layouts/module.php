<?php
/**
* @package   yoo_nano Template
* @file      module.php
* @version   1.0.0 May 2011
* @author    YOOtheme http://www.yootheme.com
* @copyright Copyright (C) 2007 - 2011 YOOtheme GmbH
* @license   YOOtheme Proprietary Use License (http://www.yootheme.com/license)
*/

// init vars
$id				= $module->id;
$position		= $module->position;
$title			= $module->title;
$showtitle		= $module->showtitle;
$content		= $module->content;
$split_color	= '';
$subtitle		= '';
$title_template	= '';

// init params
foreach (array('suffix', 'style', 'color', 'badge', 'icon') as $var) {
	$$var = isset($params[$var]) ? $params[$var] : null;
}

// test module styles here
//$style = '';
//$color = '';
//$badge = '';
//$icon = '';
//$title = '';

// force module style
if (in_array($module->position, array('mobile-logo', 'logo', 'search'))) {
	$style = 'raw';
	$showtitle = 0;
}
if (in_array($module->position, array('mobile-footer', 'footer'))) {
	$style = '';
	$showtitle = 0;
}
if ($module->position == 'menu') {
	$style = 'raw';
}

// set module template using the style
switch ($style) {

	case 'raw':
		$template		= 'raw';
		break;

	default:
		$template		= 'default-1';
		$style			= $suffix;
		$title_template = '<h3 class="title">%s</h3>';
		$subtitle		= 1;
}

// set badge if exists
if ($badge) {
	$badge = '<div class="badge badge-'.$badge.'"></div>';
}

// split title in two colors
if ($split_color) {
	$pos = mb_strpos($title, ' ');
	if ($pos !== false) {
		$title = '<span class="color">'.mb_substr($title, 0, $pos).'</span>'.mb_substr($title, $pos);
	}
}

// create subtitle
if ($subtitle) {
	$pos = mb_strpos($title, '||');
	if ($pos !== false) {
		$title = '<span class="title">'.mb_substr($title, 0, $pos).'</span><span class="subtitle">'.mb_substr($title, $pos + 2).'</span>';
	}
}

// create title icon if exists
if ($icon) {
	$title = '<span class="icon icon-'.$icon.'"></span>'.$title.'';
}

// create title template
if ($title_template) {
	$title = sprintf($title_template, $title);
}

// render menu
if ($module->menu) {

	// set menu renderer
	if (isset($params['menu'])) {
		$renderer = $params['menu'];
	} else if (in_array($module->position, array('menu'))) {
		$renderer = 'dropdown';
	} else if (in_array($module->position, array('toolbar-l', 'toolbar-r', 'footer'))) {
		$renderer = 'default';
	} else {
		$renderer = 'accordion';
	}

	// set menu style
	if ($renderer == 'dropdown') {
		$module->menu_style = 'menu-dropdown';
	} else if ($renderer == 'accordion') {
		$module->menu_style = 'menu-sidebar';
	} else if ($renderer == 'default') {
		$module->menu_style = 'menu-line';
	} else {
		$module->menu_style = null;
	}

	$content = $this['menu']->process($module, array_unique(array('pre', 'default', $renderer, 'post')));
}

// render module
echo $this->render("modules/templates/{$template}", compact('style', 'badge', 'showtitle', 'title', 'content'));