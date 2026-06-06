package com.gestex.nfse.controller;

import com.gestex.nfse.model.NfseRequest;
import com.gestex.nfse.model.NfseResponse;
import com.gestex.nfse.service.NotaFiscalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nfse")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping("/emitir/{empresaId}")
    public NfseResponse emitirNota(
            @PathVariable Long empresaId,
            @RequestBody NfseRequest request) throws Exception {
        return notaFiscalService.emitirNota(empresaId, request);
    }

    @GetMapping("/consultar/{referencia}")
    public NfseResponse consultarNota(@PathVariable String referencia) throws Exception {
        return notaFiscalService.consultarNota(referencia);
    }
}