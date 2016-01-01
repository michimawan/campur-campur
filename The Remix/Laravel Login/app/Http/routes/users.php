<?php

Route::get('users/dashboard', ['uses' => 'UsersController@dashboard']);
Route::get('users/index', ['uses' => 'UsersController@index']);