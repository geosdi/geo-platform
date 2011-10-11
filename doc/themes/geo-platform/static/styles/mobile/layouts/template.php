<?php
/**
* @package   yoo_nano Template
* @file      template.php
* @version   1.0.0 May 2011
* @author    YOOtheme http://www.yootheme.com
* @copyright Copyright (C) 2007 - 2011 YOOtheme GmbH
* @license   YOOtheme Proprietary Use License (http://www.yootheme.com/license)
*/

// get template configuration
include($this['path']->path('layouts:template.config.php'));
	
?>
<!DOCTYPE HTML>
<html lang="<?php echo $this['config']->get('language'); ?>" dir="<?php echo $this['config']->get('direction'); ?>">

<head>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;"> 
	<?php echo $this['template']->render('head'); ?>
</head>

<body id="page" class="page mobile <?php echo $this['config']->get('body_classes'); ?>">
	<?php include ("templates/yoo_nano/html/mod_search/defaults.php"); ?>
	<header id="m-toolbar" class="m-toolbar">

		<a id="m-logo" href="<?php echo $this['system']->url; ?>">
		<?php
			if ($this['modules']->count($this['config']->get('mobile_position_logo'))) {
				echo $this['modules']->render($this['config']->get('mobile_position_logo'));
			} else {
				echo '<h1>'.$this['config']->get("site_name").'</h1>';
			}
		?>
		</a>
	
		<div class="m-buttons">
			<div>
			    <div class="m-button m-menu" data-button="nav"></div>
				<?php if ($this['config']->get('mobile_search')) : ?>
			    <div class="m-button m-search" data-button="search"></div>
				<?php endif; ?>				
				<?php if ($this['config']->get('mobile_login')) : ?>
			    <div class="m-button m-login" data-button="login"></div>
				<?php endif; ?>
			</div>
	    </div>
	
		<nav id="m-menu">
		
			<div class="m-toolbar">
				<h1>Menu</h1>
				<div class="m-buttons">
					<div>
						<div class="m-button m-close"></div>
					</div>
				</div>
			</div>
			
			<div id="m-navigation"></div>
			
		</nav>
		
		<div id="m-search">
		
			<div class="m-toolbar">
				<h1>Search</h1>
				<div class="m-buttons">
					<div>
						<div class="m-button m-close"></div>
					</div>
				</div>
			</div>
			
			<div class="m-content"><?php echo $this->render("mobile/search");?></div>
			
		</div>
		
		<div id="m-login">
		
			<div class="m-toolbar">
				<h1>Login</h1>
				<div class="m-buttons">
					<div>
						<div class="m-button m-close"></div>
					</div>
				</div>
			</div>
			
			<div class="m-content"><?php echo $this->render("mobile/login");?></div>
			
		</div>
	
	</header>

	<?php if ($this['modules']->count($this['config']->get('mobile_position_top'))) : ?>
	<section id="m-top"><?php echo $this['modules']->render($this['config']->get('mobile_position_top')); ?></section>
	<?php endif; ?>
	
	<section id="m-content"><?php echo $this->render('content'); ?></section>
	
	<?php if ($this['modules']->count($this['config']->get('mobile_position_bottom'))) : ?>
	<section id="m-bottom"><?php echo $this['modules']->render($this['config']->get('mobile_position_bottom')); ?></section>
	<?php endif; ?>
	
	<a id="m-desktop" href="<?php echo $this['system']->url;?>/?mobile=0">Switch to Desktop Version</a>
	
	<?php if ($this['modules']->count('footer') || $this['config']->get('warp_branding')) : ?>
	<footer id="m-footer">
		<?php
			echo $this['modules']->render($this['config']->get('mobile_position_footer'));
			$this->output('warp_branding');
		?>
	</footer>
	<?php endif; ?>

	<div id="m-navigation-storage"><?php echo $this['modules']->render($this['config']->get('mobile_position_menu'), array("menu" => "mobile")); ?></div>

</body>

</html>