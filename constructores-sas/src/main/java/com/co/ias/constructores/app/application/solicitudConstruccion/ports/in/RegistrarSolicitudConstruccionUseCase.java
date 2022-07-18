package com.co.ias.constructores.app.application.solicitudConstruccion.ports.in;


import com.co.ias.constructores.app.application.solicitudConstruccion.model.SolicitudConstruccionDBO;
import com.co.ias.constructores.app.commons.UseCase;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public interface RegistrarSolicitudConstruccionUseCase  extends UseCase<SolicitudConstruccionDBO,Mono<Disposable>>  {

}
