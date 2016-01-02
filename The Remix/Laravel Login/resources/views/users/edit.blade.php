<!-- resources/views/registers/registration.blade.php -->
<?
@extends('layout.global')

@section('content')
	{!! Form::open(['url' => 'users/update', 'files' => true]) !!}
		<h2>Register User</h2>
		@if (count($errors) > 0)
		    <div class="alert alert-danger">
		        <ul>
		            @foreach ($errors->all() as $error)
		                <li>{{ $error }}</li>
		            @endforeach
		        </ul>
		    </div>
		@endif
		{!! Form::text('id', $user->id, ['class' => 'hidden']) !!}
		<div class='form-group'>
	    {!! Form::label('email', 'Email Address') !!}
	    {!! Form::text('email', $user->email, ['required', 'disabled','placeholder' => 'awesome@awesome.com', 'class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('name', 'Name') !!}
	    {!! Form::text('name', $user->name, ['required', 'placeholder' => 'awesome', 'class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('username', 'Username') !!}
	    {!! Form::text('username', $user->username, ['required', 'placeholder' => 'username', 'class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('password', 'Password (leave blank if not changed)') !!}
	    {!! Form::password('password', ['class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('password_confirm', 'Password Confirmation') !!}
	    {!! Form::password('password_confirm', ['class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('photo', 'Upload Photo') !!}
	    {!! Form::file('photo', ['class' => 'form-control']) !!}
		</div>
		
		<div class='form-group'>
	    {!! Form::label('role', 'User Role') !!}
	    {!! Form::select('role', ['admin' => 'Admin', 'owner' => 'Owner', 'staff' => 'Staff'], $user->role, ['class' => 'form-control', 'disabled']) !!}
		</div>

		<div class='form-group'>
		{!! Form::submit('Update !', ['class' => 'form-control btn btn-primary']) !!}
		</div>
	{!! Form::close() !!}
@stop
?>