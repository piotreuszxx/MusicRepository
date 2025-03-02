import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IArtistRequest} from '../types';


@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private baseUrl = "http://localhost:8080/api/artists";

  constructor(private http: HttpClient) {}

  getAll<T>() {
    return this.http.get<T>(this.baseUrl);
  }

  create<T>(data: IArtistRequest) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<T>(this.baseUrl, data, { headers: headers });
  }

  update<T>(id: string, data: IArtistRequest) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.patch<T>(this.baseUrl + '/' + id, data, { headers: headers });
  }

  delete<T>(id: string) {
    return this.http.delete<T>(this.baseUrl + '/' + id);
  }

}

