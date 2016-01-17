<?php

    $config = [
        "base_url" => "",
        "providers" => [
            "Google" => [ 
                "enabled" => true,
                "keys" => ["id" => "", 
                            "secret" => ""],
            ],
            "Facebook" => [
                "enabled" => true,
                "keys" => ["id" => "", 
                            "secret" => ""],
                "scope"   => "email, public_profile, user_about_me, user_birthday, user_hometown",

            ],
            "Twitter" => [
                "enabled" => true, 
                "keys" => ["key" => "", 
                            "secret" => ""],
            ],
        ],
        "debug_mode" => false,
        "debug_file" => "",
    ];

    return $config;  