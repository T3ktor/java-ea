package soapclient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(name = "MNBArfolyamServiceSoap", targetNamespace = "http://www.mnb.hu/webservices/")
public interface MNBArfolyamServiceSoap {

    @WebMethod(operationName = "GetCurrencies", action = "http://www.mnb.hu/webservices/GetCurrencies")
    @WebResult(name = "GetCurrenciesResult", targetNamespace = "http://www.mnb.hu/webservices/")
    @RequestWrapper(localName = "GetCurrencies", targetNamespace = "http://www.mnb.hu/webservices/")
    @ResponseWrapper(localName = "GetCurrenciesResponse", targetNamespace = "http://www.mnb.hu/webservices/")
    String getCurrencies() throws Exception;

    @WebMethod(operationName = "GetExchangeRates", action = "http://www.mnb.hu/webservices/GetExchangeRates")
    @WebResult(name = "GetExchangeRatesResult", targetNamespace = "http://www.mnb.hu/webservices/")
    @RequestWrapper(localName = "GetExchangeRates", targetNamespace = "http://www.mnb.hu/webservices/")
    @ResponseWrapper(localName = "GetExchangeRatesResponse", targetNamespace = "http://www.mnb.hu/webservices/")
    String getExchangeRates(
            @WebParam(name = "startDate", targetNamespace = "http://www.mnb.hu/webservices/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://www.mnb.hu/webservices/") String endDate,
            @WebParam(name = "currencyNames", targetNamespace = "http://www.mnb.hu/webservices/") String currencyNames
    ) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;

    @WebMethod(operationName = "GetCurrentExchangeRates", action = "http://www.mnb.hu/webservices/GetCurrentExchangeRates")
    @WebResult(name = "GetCurrentExchangeRatesResult", targetNamespace = "http://www.mnb.hu/webservices/")
    @RequestWrapper(localName = "GetCurrentExchangeRates", targetNamespace = "http://www.mnb.hu/webservices/")
    @ResponseWrapper(localName = "GetCurrentExchangeRatesResponse", targetNamespace = "http://www.mnb.hu/webservices/")
    String getCurrentExchangeRates() throws MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage;

    @WebMethod(operationName = "GetInfo", action = "http://www.mnb.hu/webservices/GetInfo")
    @WebResult(name = "GetInfoResult", targetNamespace = "http://www.mnb.hu/webservices/")
    @RequestWrapper(localName = "GetInfo", targetNamespace = "http://www.mnb.hu/webservices/")
    @ResponseWrapper(localName = "GetInfoResponse", targetNamespace = "http://www.mnb.hu/webservices/")
    String getInfo() throws MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage;
}
