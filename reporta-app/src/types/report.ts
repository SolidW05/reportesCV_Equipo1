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

export type ReportStatus = 'PENDING' | 'IN_REVIEW' | 'RESOLVED' | 'REJECTED';

export const STATUS_LABELS: Record<string, string> = {
  PENDING: 'Pendiente',
  IN_REVIEW: 'En Revisión',
  RESOLVED: 'Resuelto',
  REJECTED: 'Rechazado',
};

export const STATUS_COLORS: Record<string, string> = {
  PENDING: 'status-pending',
  IN_REVIEW: 'status-review',
  RESOLVED: 'status-resolved',
  REJECTED: 'status-rejected',
};
