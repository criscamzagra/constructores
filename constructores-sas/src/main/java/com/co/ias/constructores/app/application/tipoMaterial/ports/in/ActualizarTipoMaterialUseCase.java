package com.co.ias.constructores.app.application.tipoMaterial.ports.in;

import com.co.ias.constructores.app.application.tipoMaterial.model.TipoMaterialDBO;
import com.co.ias.constructores.app.commons.UseCase;

import reactor.core.publisher.Mono;

public interface ActualizarTipoMaterialUseCase extends UseCase<String,Mono<TipoMaterialDBO>> {

}
