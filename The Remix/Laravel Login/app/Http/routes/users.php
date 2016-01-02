<?php

Route::group(['middleware' => 'role:admin,owner,staff'], function() {
	Route::get('users/dashboard', ['uses' => 'UsersController@dashboard']);
	Route::get('users/edit/{id}', ['uses' => 'UsersController@edit']);
	Route::post('users/update', ['uses' => 'UsersController@update']);
});

Route::group(['middleware' => 'role:admin,owner'], function() {
	Route::get('users/index', ['uses' => 'UsersController@index']);
	Route::get('users/delete/{id}', ['uses' => 'UsersController@deactive']);
});