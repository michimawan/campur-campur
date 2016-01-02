<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Requests;
use App\Models\User;
use Validator;
use Redirect;
use Input;
use Image;
use View;
use Auth;
use Hash;

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
            'password' => Input::get('password'),
            'status' => 0
        ];

        $err = [
            'messages' => 'Your account is inactive',
            'class' => 'danger'
        ];

        if(Auth::attempt($user)) {
            return Redirect::to('login')->with($err);
        }

        $user['status'] = 1;
        if(Auth::attempt($user)) {
            return Redirect::to('users/dashboard');
        }

        $err['messages'] = 'Invalid Username/Password';
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
            'photo' => 'image|mimes:jpeg,jpg,bmp,png|max:2000'
        ];

        $validation = Validator::make(Input::all(), $rules);
        if($validation->fails()) {
            return Redirect::to('register')->withErrors($validation)->withInput();
        }

        $ext = Input::file('photo')->getClientOriginalExtension();
        $destinationPath = 'img/uploads/';
        $filename = Input::get('email');
        $fullname = rand(11111, 99999).'_'.$filename.'.'.$ext;

        $success = Input::file('photo')->move($destinationPath, $fullname);

        // resize the image to a width of 300 and constrain aspect ratio (auto height)
        Image::make($success)->resize(110, null, function ($constraint) {
            $constraint->aspectRatio();
        })->save('img/thumbs/'.$fullname);

        $user = new User;
        $user->name = Input::get('name');
        $user->username = Input::get('username');
        $user->email = Input::get('email');
        $user->password = Hash::make(Input::get('password'));
        $user->photo = $fullname;

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
