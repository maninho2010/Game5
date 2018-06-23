$(function(){
	
	var containerGame = $('.game');
	var qtdPalavrasRegistradas = containerGame.data('qtd-palavra-cadastradas');
	
	if(qtdPalavrasRegistradas > 0 ){
		var palavra = "";
		var palavraUnderlines = $('.palavra-underlines');
		var botaoChute = $('#btn-chute');
		var inputChute = $('#input-chute');
		var spanTema = $('#tema');
		var spanNivel = $('#nivel');
		var spanChances = $('#chances');
		var outPutMessage = $("#chute-output-message");
		
	
		inicializar();
		
		inputChute.on('input', function(){outPutMessage.text('')});
		botaoChute.click(chutou);
	}
	function chutou(){
		var chute = inputChute.val().trim();
	
		if(chute == palavra){
			acertou();
			inicilizar();
		}
		else{
			var chances = parseInt(spanChances.text());
			chances --;
			spanChances.text(chances);
			
			if(chances <= 0) perdeu();
			else errou();
		}
	}
	
	function acertou(){
		window.alert("Parabens, você acertou!");
		inputChute.val('');
		inicializar();
	}
	
	function errou(){
		outPutMessage.text('Errou');
		outPutMessage.css('color', 'red');
		inputChute.val('');
	}
	
	function inicializar(){
		
		var resposta = $.ajax({
			url: containerGame.data('url'),
			method: 'GET',
			contentType: 'application/json',
		});
		
		resposta.done(setarVariaveis);
	}
	
	function setarVariaveis(resposta){
		
		inputChute.val('');
		palavra = resposta.nome;
		palavraUnderlines.text(resposta.underlines);
		spanTema.text(resposta.tema.nome);
		spanNivel.text(resposta.nivel);
		spanChances.text('5');
		outPutMessage.text('');
	}
	
	function perdeu(){
		window.alert('perdeu');
		inicializar();
	}
	
});