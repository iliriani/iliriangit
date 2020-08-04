rule C2C 
	match left : Left!EClass 
	with right : Right!EClass { 
	
	compare {
		
		return isSynonym(left.name, right.name);
	}
}

operation isSynonym(s1 : String, s2 : String) {
	return s1 = s2;
}