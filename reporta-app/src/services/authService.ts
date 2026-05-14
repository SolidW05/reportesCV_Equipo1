/**
 * authService.ts — Login and registration API calls (server-side, direct to backend)
 * These use the full localhost URL because they run from Astro SSR frontmatter,
 * not through the Vite dev-server proxy.
 */

import { apiFetch2 } from "./api";

const BACKEND = ''; //'http://localhost:8080';

export interface LoginResponse {
  token: string;
}

export async function loginUser(
  email: string,
  password: string
): Promise<LoginResponse> {
  const res = await apiFetch2(`/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!res.ok) {
    let msg = `Error ${res.status}`;
    try {
      const body = await res.json();
      msg = body?.message ?? body?.error ?? msg;
    } catch { }
    throw new Error(msg);
  }
  return res.json();
}

export async function registerUser(
  name: string,
  email: string,
  password: string
 ): Promise<void> {
  const res = await apiFetch2(`/users`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, email, password }),
  });
  if (!res.ok) {
    let msg = `Error ${res.status}`;
    try {
      const body = await res.json();
      msg = body?.message ?? body?.error ?? msg;
    } catch { }
    throw new Error(msg);
  }
}

export async function checkIsAutoridad(userId: number, token: string): Promise<boolean> {
  try {
    const res = await apiFetch2(`/autoridad/usuario/${userId}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return res.ok;
  } catch {
    return false;
  }
}
