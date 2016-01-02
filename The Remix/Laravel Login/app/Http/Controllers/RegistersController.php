<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Requests;
use App\Models\User;
use Validator;
use Redirect;
use Input;
use View;
use Auth;

class RegistersController extends Controller
{
    public function login()
    {
        return View::make('registers.login')->with(['title' => 'Login Page']);
    }

    public function authenticate()
    {
        $user = [
            'email' => Input::get('email'),
            'password' => Input::get('password')
        ];

        if(Auth::attempt($user)) {
            return Redirect::to('users/index');
        }

        $err = [
            'messages' => 'Invalid Username/Password',
            'class' => 'danger'
        ];
        return Redirect::to('login')->with($err);
    }

    public function register()
    {
        return View::make('registers.registration')->with(['title' => 'Registration Page']);
    }

    public function store()
    {
        $rules = [
            'email' => 'email|unique:users',
            'password' => 'same:password_confirm',
        ];

        $validation = Validator::make(Input::all(), $rules);
        if($validation->fails()) {
            return Redirect::to('register')->withErrors($validation)->withInput();
        }

        $user = new User;
        $user->name = Input::get('name');
        $user->username = Input::get('username');
        $user->email = Input::get('email');
        $user->password = Hash::make(Input::get('password'));

        if($user->save()) {
            Auth::loginUsingId($user->id);
            return Redirect::to('users/dashboard');
        }

        return Redirect::to('register')->withInput();
    }

    public function logout()
    {
        Auth::logout();
        return Redirect::to('login');
    }
}
