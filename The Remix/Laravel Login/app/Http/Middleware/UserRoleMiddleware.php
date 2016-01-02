<?php

namespace App\Http\Middleware;

use Closure;
use Redirect;

class UserRoleMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next, $role1 = null, $role2 = null, $role3 = null)
    {
        if(auth()->check()) {
            if(auth()->user()->hasRole($role1) || auth()->user()->hasRole($role2) || 
                auth()->user()->hasRole($role3))
                return $next($request);
            else 
                return Redirect::back();
        } else 
            return Redirect::to('login');
    }
}
