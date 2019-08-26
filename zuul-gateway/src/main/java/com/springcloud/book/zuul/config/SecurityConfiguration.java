package com.springcloud.book.zuul.config;


// @Configuration
// @EnableOAuth2Sso
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//     // @Override
//     // protected void configure(HttpSecurity http) throws Exception {
//     // 	http
//     // 	.requestMatcher(EndpointRequest.toAnyEndpoint())
//     // 	.authorizeRequests()
//     // 	.anyRequest()
//     // 	.permitAll();
//     // }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//             .antMatchers("/login", "/client/**")
//             .permitAll()
//             .anyRequest()
//             .authenticated()
//             .and()
//             .csrf()
//             .disable();
//     }
// }