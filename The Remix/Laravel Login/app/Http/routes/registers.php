<?php
Route::get('login', ['uses' => 'RegistersController@login']);
Route::post('login', ['uses' => 'RegistersController@authenticate']);

Route::get('register', ['uses' => 'RegistersController@register']);
Route::post('register', ['uses' => 'RegistersController@store']);

Route::group(['middleware' => 'role:admin,owner,staff'], function() {
	Route::get('logout', ['uses' => 'RegistersController@logout']);
});