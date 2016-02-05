<!-- resources/views/registers/registration.blade.php -->
<?
@extends('layout.global')

@section('content')
	{!! Form::open(['url' => 'store']) !!}
		<h2>Add Document</h2>

		<div class='form-group'>
	    {!! Form::label('id', 'Id Document') !!}
	    {!! Form::text('id', Input::old('id'), ['required', 'placeholder' => '1122131', 'class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
	    {!! Form::label('content', 'Doc Content') !!}
	    {!! Form::textarea('content', Input::old('content'), ['required', 'placeholder' => 'You are haw some', 'class' => 'form-control']) !!}
		</div>

		<div class='form-group'>
		{!! Form::submit('Add Doc !', ['class' => 'form-control btn btn-primary']) !!}
		</div>
	{!! Form::close() !!}
@stop
?>