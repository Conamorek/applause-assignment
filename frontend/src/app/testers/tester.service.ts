import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tester} from './model/tester.model';

@Injectable({
  providedIn: 'root'
})
export class TesterService {

  constructor(private httpClient: HttpClient) { }

  findTesters(countries: string[], devices: string[]): Observable<Tester[]> {
    return this.httpClient.post<Tester[]>('http://localhost:8080/find', {countries: countries, devices: devices});
  }
}
