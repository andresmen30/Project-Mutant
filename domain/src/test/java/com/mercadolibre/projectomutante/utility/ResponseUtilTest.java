package com.mercadolibre.projectomutante.utility;

import com.mercadolibre.projectomutante.data.ResultStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ResponseUtilTest {

    private ResponseUtil responseUtil;

    @Test
    void resultOk() {
        init();
        Assertions.assertEquals(responseUtil.resultOk("Mensaje OK").getStatusCodeValue(), 200);
    }

    @Test
    void resultForbidden() {
        init();
        Assertions.assertEquals(responseUtil.resultForbidden("Mensaje Forbidden").getStatusCodeValue(), 403);
    }

    @Test
    void resultError() {
        init();
        Assertions.assertEquals(responseUtil.resultError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                "Mensaje Not Found").getStatusCodeValue(), 404);
    }

    @Test
    void resultStatus() {
        init();
        final ResultStats resultStats = new ResultStats();
        resultStats.setRatio(0);
        resultStats.setCountMutantDna(0);
        resultStats.setCountHumantDna(0);
        Assertions.assertEquals(responseUtil.resultStatus(HttpStatus.OK,
                "Mensaje OK", resultStats).getStatusCodeValue(), 200);
    }

    private void init() {
        responseUtil = new ResponseUtil();
    }
}