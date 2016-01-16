<?php

namespace App\Http\Controllers;

use View;
use Config;
use Redirect;
use App\User;
use Validator;
use Hybrid_Auth;
use App\Http\Controllers\Controller;
use App\Services\SocialLoginService;
use Illuminate\Foundation\Auth\ThrottlesLogins;
use Illuminate\Foundation\Auth\AuthenticatesAndRegistersUsers;

class LoginController extends Controller
{
    // must set $auth as null, and send as param to prevent redirect loop
    public function socialLogin($provider = null, $auth = null)
    {
        $profile = SocialLoginService::getSocialProfile($provider, $auth);
        dd($profile);

        // do authentication and session in here
    }

    public function logout()
    {
        $hauth = new Hybrid_Auth(Config::get('hybridauth'));
        $hauth->logoutAllProviders();
        return Redirect::to('login');
    }
}
