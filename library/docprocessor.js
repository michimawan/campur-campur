$(document).ready(function() {
	var name_fruit = new Array();
	var price_fruit = new Array();
	var profit_fruit = new Array();
	var duration_fruit = new Array();
	var total_fruit = 0;
	var modal = 0;

	$('#second-form').hide();
	$('.js-single-fruit-form-xxx').hide();
	$('.js-formSpreadSheet').hide();

	$('.js-form1-submit').click(function() {
		total_fruit = $('.js-jmlvariable-form1').val();
		
		$('#first-form').hide();

		fill_form2();

		$('#second-form').show();
	});

	function fill_form2() {
		for(var i=0; i<total_fruit; i++) {
			var str = $('.js-single-fruit-form-xxx').html().toString();
			str = '<div class="js-single-fruit-form">' + str + '</div>';
			str = str.split("xxx").join(i+1);
			$('.js-form2-submit').before(str);
		}		
	}

	$('.js-form2-submit').click(function() {
		$single_form = $('.js-single-fruit-form');
		modal = $('.js-modalvariable-form1').val();
		var i = 1;
		var str = "Maximize p = (1/2)x + 3y + z + 4w subject to" + cr + "x + y + z + w <= 40" + cr + "2x + y - z - w >= 10" + cr + "w - y >= 10";
		str = "Maximize ";
		var cr = unescape( "%0D" );	

		$single_form.each(function() {
			name_fruit.push( $(this).find('.js-varname-'+i).val() );
			price_fruit.push( $(this).find('.js-varprice-'+i).val() );
			profit_fruit.push( $(this).find('.js-varprofit-'+i).val() );
			duration_fruit.push( $(this).find('.js-varduration-'+i).val() );
			
			i++;
		});
		
		i = 0;
		var goal_str = "Z = ";
		var price_str = "";
		var duration_str = "";
		for(; i<total_fruit; i++){
			if(i == total_fruit-1){
				goal_str += profit_fruit[i] + name_fruit[i].substr(0,3);
				price_str += price_fruit[i] + name_fruit[i].substr(0,3);
			} else {
				goal_str += profit_fruit[i] + name_fruit[i].substr(0,3) + " + ";
				price_str += price_fruit[i] + name_fruit[i].substr(0,3) + " + ";	
			}
			duration_str += name_fruit[i].substr(0,3) + " <= " +(parseInt(duration_fruit[i]) * 24) + cr;
		}
		goal_str += cr;
		price_str +=  " <= " + modal + cr;

		str += goal_str + price_str + duration_str;	

		$('form[name="theSpreadsheet"]').find('textarea[name="input"]').text(str);
		$("#second-form").hide();
		$('.js-formSpreadSheet').show();
	});

	$('.js-reset').click(function() {
		window.location.reload();
	});
});