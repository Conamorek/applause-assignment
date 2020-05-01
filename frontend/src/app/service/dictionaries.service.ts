import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DictionariesService {

  private readonly dictionariesUrl: string;

  constructor(private httpClient: HttpClient) {
    this.dictionariesUrl = 'http://localhost:8080/dictionary';
  }

  loadAllDictionaries(): Observable<Map<string, []>> {
    return this.httpClient.get<Map<string, []>>(this.dictionariesUrl);
  }
}
