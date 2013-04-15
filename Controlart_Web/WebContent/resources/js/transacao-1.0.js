function func_transacao() {
	$(".moeda").maskMoney({
		showSymbol : true,
		symbol : "R$ ",
		decimal : ",",
		thousands : "."
	});
}