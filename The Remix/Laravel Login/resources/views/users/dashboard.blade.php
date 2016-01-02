<!-- resources/views/users/dashboard.blade.php -->
<?
@extends('layout.global')

@section('content')

	<div class="row">
	  	<div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	      	<img src="..." alt="...">
	      	<div class="caption">
	        	<h3>{{ $user->username }}</h3>
	        	<h5>{{ $user->email }}</h5>
	        	<p>{!! Html::link('users/edit/'.$user->id, 'Edit', ['class' => 'btn btn-warning']) !!}</p>
	      </div>
	    </div>
	  	</div>
	</div>
@stop
?>