/**
 * auth.ts — JWT helpers for ReporTA
 * The JWT is stored as a regular cookie (non-httpOnly) so both Astro SSR
 * frontmatter and client-side scripts can read it.
 */

export interface JwtPayload {
  sub: string;                     // email
  tipoUser: string;
  id: number;                      // user id
  iat: number;
  exp: number;
}

/**
 * Decode a JWT token and return its payload.
 * Does NOT verify the signature — verification is done by Spring Boot on every API call.
 */
export function decodeJwt(token: string): JwtPayload | null {
  try {
    const parts = token.split('.');
    if (parts.length !== 3) return null;
    // Base64url → Base64
    const b64 = parts[1].replace(/-/g, '+').replace(/_/g, '/');
    const padded = b64.padEnd(b64.length + (4 - (b64.length % 4)) % 4, '=');
    return JSON.parse(atob(padded)) as JwtPayload;
  } catch {
    return null;
  }
}

export function isTokenExpired(payload: JwtPayload): boolean {
  return Date.now() / 1000 > payload.exp;
}

/** Cookie name used throughout the app */
export const JWT_COOKIE = 'jwt';
export const ROLE_COOKIE = 'user_role';

/** Cookie set options */
export const COOKIE_OPTIONS = {
  path: '/',
  sameSite: 'lax' as const,
  maxAge: 60 * 60 * 24, // 24 hours
};

export function isUsuario(role?: string): boolean {
  if (!role) return false;
  const r = role.toLowerCase();
  return r === 'usuario' || r === 'user' || r === 'role_usuario' || r === 'role_user';
}

export function isAutoridad(role?: string): boolean {
  if (!role) return false;
  const r = role.toLowerCase();
  return r === 'autoridad' || r === 'authority' || r === 'role_autoridad' || r === 'admin' || r === 'role_admin';
}

export function roleRedirect(role?: string): string {
  if (isAutoridad(role)) return '/admin/reports';
  return '/reports';
}

