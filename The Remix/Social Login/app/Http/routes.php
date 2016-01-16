<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return View::make('login');
});

// Route::get('twauth/{auth?}', ['uses' => 'LoginController@socialLogin']);
// Route::get('fbauth/{auth?}', ['uses' => 'LoginController@socialLogin']);
// Route::get('gauth/{auth?}', ['uses' => 'LoginController@socialLogin']);

Route::get('login/{provider}/{auth?}', ['uses' => 'LoginController@socialLogin']);

Route::get('logout', ['uses' => 'LoginController@logout']);