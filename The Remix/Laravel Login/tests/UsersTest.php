<?php

use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\DatabaseTransactions;

class UsersTest extends TestCase
{
    /**
     * A basic test example.
     *
     * @return void
     */
    use DatabaseTransactions;

    public function test_user_creation()
    {
    	factory(App\Models\User::class)->create([
    		'name' => 'Who'
    	]);
        $this->seeInDatabase('users', ['name' => 'Who']);
    }
}
