import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { TrainDto } from './train.models';

@Injectable({ providedIn: 'root' })
export class TrainApi {
  private base = `${environment.api}/train`;
  constructor(private http: HttpClient) {}
  get(): Observable<TrainDto> { return this.http.get<TrainDto>(this.base); }
  validate(): Observable<boolean> { return this.http.get<boolean>(`${this.base}/validate`); }
  reset(compact: string): Observable<TrainDto> { return this.http.post<TrainDto>(`${this.base}/reset/${compact}`, {}); }
  addLeft(type: string): Observable<TrainDto> { return this.http.post<TrainDto>(`${this.base}/left/add`, { type }); }
  addRight(type: string): Observable<TrainDto> { return this.http.post<TrainDto>(`${this.base}/right/add`, { type }); }
  removeLeft(): Observable<TrainDto> { return this.http.delete<TrainDto>(`${this.base}/left`); }
  removeRight(): Observable<TrainDto> { return this.http.delete<TrainDto>(`${this.base}/right`); }
  replace(index: number, type: string): Observable<TrainDto> { return this.http.put<TrainDto>(`${this.base}`, { index, type }); }
  sort(): Observable<TrainDto> { return this.http.post<TrainDto>(`${this.base}/sort`, {}); }
}
