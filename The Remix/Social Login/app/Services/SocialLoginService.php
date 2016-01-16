<?php

namespace App\Services;

use Config;
use Redirect;
use Hybrid_Auth;
use Hybrid_Endpoint;

class SocialLoginService
{
	public function __construct()
	{
	}

	public static function getSocialProfile($provider = null, $auth = null)
    {
        if($auth == 'auth') {
            try {
                Hybrid_Endpoint::process();
            } catch(Exception $e) {
                return Redirect::to('login');
            }
        return ;
        }

        if($provider) {
            if(Config::get('hybridauth.base_url') == "")
                self::setBaseUrl($provider);

            $oauth = new Hybrid_Auth(Config::get('hybridauth'));
            
            $adapter = $oauth->authenticate($provider);
            $profile = $adapter->getUserProfile();
            
            return $profile;
        }
        return null;
    }

    private static function setBaseUrl($provider = null)
    {
    	switch (strtolower($provider)) {
    		case 'google':
    			Config::set('hybridauth.base_url', 'http://www.dummy.ex.com/login/google/auth');
    			break;
    		case 'facebook':
    			Config::set('hybridauth.base_url', 'http://www.dummy.ex.com/login/facebook/auth');
    			break;
    		case 'twitter':
    			Config::set('hybridauth.base_url', 'http://www.dummy.ex.com/login/twitter/auth');
    			break;
    	}
    }
}