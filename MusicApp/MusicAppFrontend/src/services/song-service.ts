import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ISongRequest, ISongResponse} from '../types';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SongService {
  private baseUrl = "http://localhost:8080/api/songs";

  constructor(private http: HttpClient) {}

  getAll<T>() {
    return this.http.get<T>(this.baseUrl);
  }

  findById(id: string) {
    return this.http.get<ISongResponse>(`${this.baseUrl}/${id}`);
  }

  getArtistSongs<T>(artistId: string) {
    return this.http.get<T>("http://localhost:8080/api/artists/" + artistId + "/songs");
  }

  create<T>(data: ISongRequest) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<T>(this.baseUrl, data, { headers: headers });
  }

  update<T>(id: string, data: ISongRequest) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.patch<T>(this.baseUrl + '/' + id, data, { headers: headers });
  }

  delete<T>(id: string) {
    return this.http.delete<T>(this.baseUrl + '/' + id);
  }

}
