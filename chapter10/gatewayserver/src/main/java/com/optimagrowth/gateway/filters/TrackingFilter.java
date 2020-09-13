package com.optimagrowth.gateway.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

	private final FilterUtils filterUtils;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
		if (isCorrelationIdPresent(requestHeaders)) {
			log.debug("tmx-correlation-id found in tracking filter: {}. ",
					filterUtils.getCorrelationId(requestHeaders));
		} else {
			String correlationID = generateCorrelationId();
			exchange = filterUtils.setCorrelationId(exchange, correlationID);
			log.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID);
		}
		
		log.info("The authentication name from the token is : " + getAuthenticationName(requestHeaders));

		return chain.filter(exchange);
	}


	private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
		return filterUtils.getCorrelationId(requestHeaders) != null;
	}

	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

	private String getAuthenticationName(HttpHeaders requestHeaders){
		String authenticationName = "";
		if (filterUtils.getAuthToken(requestHeaders)!=null){
			String authToken = filterUtils.getAuthToken(requestHeaders).replace("Bearer ","");
	        JSONObject jsonObj = decodeJWT(authToken);
	        authenticationName = jsonObj.getString("authentication_name");
		}
		return authenticationName;
	}


	private JSONObject decodeJWT(String JWTToken) {
		String[] split_string = JWTToken.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		JSONObject jsonObj = new JSONObject(body);
		return jsonObj;
	}

}