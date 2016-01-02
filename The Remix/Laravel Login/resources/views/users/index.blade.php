<!-- resources/views/users/index.blade.php -->
<?
@extends('layout.global')

@section('content')
	@if (Session::get('messages') !== null)
	<div class="alert alert-{{ Session::get('class') }}" >
	{{ Session::get('messages') }}
	</div>
	@endif
	<div class="row">
	@foreach ($users as $user)
	  	<div class="col-sm-6 col-md-4">
	    <div class="thumbnail">
	      	{!! Html::image('img/default.png') !!}
	      	<div class="caption">
	        	<h3>{{ $user->username }}</h3>
	        	<h5>{{ $user->email }}</h5>
	        	<p>{!! Html::link('users/edit/'.$user->id, 'Edit', ['class' => 'btn btn-warning']) !!} {!! Html::link('users/delete/'.$user->id, 'Deactive', ['class' => 'btn btn-danger']) !!}</p>
	      </div>
	    </div>
	  	</div>
	@endforeach
	</div>
@stop
?>