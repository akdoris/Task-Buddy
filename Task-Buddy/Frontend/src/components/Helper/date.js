export function formatDate(dateValue) {
  const date = new Date(dateValue);
  return date.toISOString().slice(0, 10);
}

export function isOverdue(dateValue) {
  const today = new Date();
  today.setHours(0, 0, 0, 0);
  const due = new Date(dateValue);
  due.setHours(0, 0, 0, 0);
  return due < today;
}
