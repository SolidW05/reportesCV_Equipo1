import type { ReportCreateDto } from '../types/report';

/**
 * Builds a FormData payload for creating/updating a report.
 * Uses multipart/form-data with a JSON "report" part and an optional "image" file.
 */
export function buildReportFormData(
  report: ReportCreateDto,
  imageFile?: File | null
): FormData {
  const fd = new FormData();
  fd.append(
    'report',
    new Blob([JSON.stringify(report)], { type: 'application/json' })
  );
  if (imageFile && imageFile.size > 0) {
    fd.append('image', imageFile);
  }
  return fd;
}
