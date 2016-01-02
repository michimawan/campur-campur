<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Requests;
use App\Models\User;
use Validator;
use File;
use Redirect;
use Auth;
use Input;
use Image;
use View;
use Hash;

class UsersController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function dashboard()
    {
        if($this->authenticate()){
            $data = [
                'user' => Auth::user(),
                'title' => 'Profile Page'
            ];
            return View::make('users.dashboard')->with($data);
        }
    }

    public function index()
    {
        if($this->authenticate()) {
            $data = [
                'users' => User::all()->where('status', 1),
                'title' => 'List of Users'
            ];
            return View::make('users.index')->with($data);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $user = User::findOrFail($id);
        return View::make('users.edit')->with(['user' => $user, 'title' => 'Edit User Profile']);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update()
    {
        $rules = [
            'password' => 'same:password_confirm',
            'photo' => 'image|mimes:jpeg,jpg,bmp,png|max:2000'
        ];

        $validation = Validator::make(Input::all(), $rules);
        if($validation->fails()) {
            return Redirect::to('users/edit/'.Input::get('id'))->withErrors($validation)->withInput();
        }

        $user = User::find(Input::get('id'));
        if(Input::file('photo')) {
            $user->photo != "" ? File::delete('img/uploads/'.$user->photo): "";
            $user->photo != "" ? File::delete('img/thumbs/'.$user->photo): "";

            $ext = Input::file('photo')->getClientOriginalExtension();
            $destinationPath = 'img/uploads/';
            $filename = Input::get('email');
            $fullname = rand(11111, 99999).'_'.$filename.'.'.$ext;

            $success = Input::file('photo')->move($destinationPath, $fullname);

            // resize the image to a width of 300 and constrain aspect ratio (auto height)
            Image::make($success)->resize(110, null, function ($constraint) {
                $constraint->aspectRatio();
            })->save('img/thumbs/'.$fullname);

            $user->photo = $fullname;
        }

        $user->name = Input::get('name');
        $user->username = Input::get('username');
        if(Input::get('password') !== "") {
            $user->password = Hash::make(Input::get('password'));
        }

        if($user->save()) {
            Auth::loginUsingId($user->id);
            return Redirect::to('users/dashboard');
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function deactive($id)
    {
        $user = User::findOrFail($id);
        if($user){
            $user->status = false;

            if($user->save()) {
                return Redirect::to('users/index')->with(['messages' => 'User has been deleted', 'class' => 'success']);
            }
            return Redirect::to('users/index')->with(['messages' => 'Failed to delete user', 'class' => 'danger']);
        }
        return Redirect::to('users/index')->with(['messages' => 'Failed to delete user', 'class' => 'danger']);
    }

    private function authenticate()
    {
        if(Auth::check()) {
            return true;
        }

        return Redirect::to('login')->with(['messages' => 'Please do Login', 'class' => 'danger']);       
    }
}