export const oktaConfig = {
    clientId: '0oa97i3mxqn6hX6d25d7',
    issuer: 'https://dev-59463338.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scope: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}