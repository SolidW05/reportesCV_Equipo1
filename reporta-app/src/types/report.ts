export interface ReportCreateDto {
  idUser: number;
  idLocation: number;
  description: string;
}

export interface ReportResponseDto {
  idReport: number;
  idLocation: number;
  description: string;
  date: string;
  observaciones: string;
  status: string;
  imageName?: string;
}

export interface ReportUpdateStatus {
  idReport: number;
  status: string;
  observaciones: string;
}

export type ReportStatus = 'PENDIENTE' | 'EN_PROCESO' | 'RESUELTO' | 'RECHAZADO';

export const STATUS_LABELS: Record<string, string> = {
  PENDIENTE: 'Pendiente',
  EN_PROCESO: 'En Proceso',
  RESUELTO: 'Resuelto',
  RECHAZADO: 'Rechazado',
};

export const STATUS_COLORS: Record<string, string> = {
  PENDIENTE: 'status-pending',
  EN_PROCESO: 'status-review',
  RESUELTO: 'status-resolved',
  RECHAZADO: 'status-rejected',
};

// ── Autoridad ──────────────────────────────────────────────────────────────
export interface AutoridadDto {
  idAutoridad: number;
  idUsuario: number;
  municipio: string; // e.g. "Acatic"
  telefono: string;
}

// ── Ubicación ──────────────────────────────────────────────────────────────
export interface LocationDto {
  id: number;
  latitud: number;
  longitud: number;
  direccionCompleta: string;
  calle: string;
  numero: string;
  colonia: string;
  codigoPostal: string;
  municipio: string;
  estado: string;
  pais: string;
  fechaConsulta: string;
}

// ── Auth ───────────────────────────────────────────────────────────────────
export interface RegisterUserDto {
  name: string;
  email: string;
  password: string;
}

export interface LoginResponseDto {
  token: string;
}

