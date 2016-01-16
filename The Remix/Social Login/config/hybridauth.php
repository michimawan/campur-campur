<?php

    $config = [
        "base_url" => "",
        "providers" => [
            "Google" => [ 
                "enabled" => true,
                "keys" => ["id" => "353882594479-bbtkg94v6oqs0v053db2irtsdq1d6267.apps.googleusercontent.com", 
                            "secret" => "Wh1xIjKfm8AmEfMqhGHPuOv6"],
            ],
            "Facebook" => [
                "enabled" => true,
                "keys" => ["id" => "1038477776172438", 
                            "secret" => "c298ac125d9fd098970c067d96bbf74b"],
                "scope"   => "email, public_profile, user_about_me, user_birthday, user_hometown",

            ],
            "Twitter" => [
                "enabled" => true, 
                "keys" => ["key" => "NtWJKqtNDJ7BLxkf1Fi981YGs", 
                            "secret" => "MYFD9MWYwmc6AkgGzkXjQ5Qo5GongK86M4dizBYxeyJmHP8zvI"],
            ],
        ],
        "debug_mode" => false,
        "debug_file" => "",
    ];

    return $config;  