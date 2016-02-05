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

Route::get('/', ['as' => 'solr.index', 'uses' => 'SolrsController@index']);
Route::get('/search', ['as' => 'solr.search', 'uses' => 'SolrsController@search']);
Route::get('/create', ['as' => 'solr.store', 'uses' => 'SolrsController@create']);
Route::post('/store', ['as' => 'solr.store', 'uses' => 'SolrsController@store']);
