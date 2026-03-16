import { apiFetch } from './api';
import type { ReportResponseDto, ReportUpdateStatus } from '../types/report';

export const getReportsByUser = (id: number) =>
  apiFetch<ReportResponseDto[]>(`/usuarios/${id}`);

export const getReport = (id: number | string) =>
  apiFetch<ReportResponseDto>(`/${id}`);

export const deleteReport = (id: number | string) =>
  apiFetch(`/${id}`, { method: 'DELETE' });

export const getReportsByMunicipio = (municipio: string) =>
  apiFetch<ReportResponseDto[]>(`/municipio/${encodeURIComponent(municipio)}`);

export const updateReportStatus = (dto: ReportUpdateStatus) =>
  apiFetch<ReportResponseDto>('/actualizar/estatus', {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dto),
  });

export const createReport = (formData: FormData) =>
  apiFetch<ReportResponseDto>('', {
    method: 'POST',
    body: formData,
  });

export const updateReport = (id: number | string, formData: FormData) =>
  apiFetch<ReportResponseDto>(`/${id}`, {
    method: 'PUT',
    body: formData,
  });
