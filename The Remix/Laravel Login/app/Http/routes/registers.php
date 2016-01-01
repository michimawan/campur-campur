<?php
Route::get('login', ['uses' => 'RegistersController@login']);
Route::post('login', ['uses' => 'RegistersController@authenticate']);
Route::post('logout', ['uses' => 'RegistersController@logout']);

Route::get('register', ['uses' => 'RegistersController@register']);
Route::post('register', ['uses' => 'RegistersController@store']);