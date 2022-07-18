package com.co.ias.constructores.app.commons;

public interface UseCase<Input, Output> {
	
	 Output execute(Input input);

}
