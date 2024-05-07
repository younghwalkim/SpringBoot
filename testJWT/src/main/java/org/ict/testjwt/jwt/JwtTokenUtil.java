package org.ict.testjwt.jwt;

import io.jsonwebtoken.*;

import java.util.*;

public class JwtTokenUtil {

    // jwt token 발급
    public static String creatToken(
            String loginId,
            String key,
            long expireTimeMs){

        // Claim : JWT Token 에 들어갈 정보
        // Claim 에 loginId 를 넣어주면, 나중에 꺼내서 사용할 수 있음.
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();
    }

    public static String createRefreshToken(
            String loginId,
            String key,
            long expireTimeMs){

        // Claim : JWT Token 에 들어갈 정보
        // Claim 에 loginId 를 넣어주면, 나중에 꺼내서 사용할 수 있음.
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (expireTimeMs * 2) ))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // Claim 에서 loginId 값 꺼내기
    public static String getLoginId(
            String token,
            String secretKey){

        return extractClaims(token, secretKey).get("loginId").toString();
    }

    // secretKey 를 이용해서 token 파싱
    public static Claims extractClaims(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // 발급된 Token 이 만료시간이 지났는지 체크
    public static boolean isExpired(String token, String secretKey){
        Date expiredDate = null;
        try{
            expiredDate = extractClaims(token, secretKey).getExpiration();
        } catch (ExpiredJwtException e){
            return true;
        }

        // 토큰의 만료날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());

    }

}
