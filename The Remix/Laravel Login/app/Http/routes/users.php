<?php

Route::group(['before' => 'auth'], function() {
	Route::get('users/', ['uses' => 'UsersController@index']);
	Route::get('users/dashboard', ['uses' => 'UsersController@dashboard']);
	Route::get('users/index', ['uses' => 'UsersController@index']);

	Route::get('users/edit/{id}', ['uses' => 'UsersController@edit']);
	Route::post('users/update', ['uses' => 'UsersController@update']);
	Route::get('users/delete/{id}', ['uses' => 'UsersController@deactive']);
});