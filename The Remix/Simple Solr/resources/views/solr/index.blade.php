<!-- resources/views/results/index.blade.php -->
@extends('layout.global')

@section('content')

<div class='row'>
	<div class='col-sm-12 col-md-12'>
	{!! Form::open(['url' => 'search', 'method'=>'get']) !!}
	<div class='form-group'>
	    {!! Form::text('content', Input::old('id'), ['required', 'placeholder' => '1122131', 'class' => 'form-control']) !!}
	</div>

	<div class='form-group'>
		{!! Form::submit('Search !', ['class' => 'form-control btn btn-primary']) !!}
	</div>
	{!! Form::close() !!}
	</div>
</div>
	<h2>There are {{ $results->getNumFound() }} row</h2>
	<div class="row">
	@foreach ($results as $result)
	  	<div class="col-sm-12 col-md-12">
	        <h3>{{ $result->id }}</h3>
	        <p>{{ $result->content }}</p>
	  	</div>
	@endforeach
	</div>
@stop
