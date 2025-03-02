import {Component, OnInit} from '@angular/core';
import {IArtistResponse} from '../../types';
import {ArtistService} from '../../services/artist-service';
import {CommonModule} from '@angular/common';
import {ArtistCreateFormComponent} from '../artist-create-form/artist-create-form.component';
import {RouterLink, RouterOutlet} from '@angular/router';
import {SongsComponent} from '../songs/songs.component';
import {SongCreateFormComponent} from '../song-create-form/song-create-form.component';

@Component({
  selector: 'app-artists',
  imports: [
    CommonModule,
    ArtistCreateFormComponent,
    RouterOutlet,
    RouterLink,
    SongsComponent,
    SongCreateFormComponent
  ],
  templateUrl: './artists.component.html',
  styleUrl: './artists.component.css'
})
export class ArtistsComponent implements OnInit {
  artists: IArtistResponse[] | null = null;

  constructor(private artistService: ArtistService) {}

  ngOnInit() {
    this.fetchArtists();
  }

  fetchArtists() {
    this.artistService.getAll<IArtistResponse[]>()
      .subscribe({
        next: (data) => {
          this.artists = data
        },
        error: (err) => {
          console.log('Error getting artist data', err)
        }
      })
  }

  onArtistCreated(newArtist: IArtistResponse) {
    if(this.artists){
      this.artists.push(newArtist)
    }
  }

  deleteArtist(id: string) {
    this.artistService.delete(id)
      .subscribe({
        next: () => {
          this.artists = this.artists?.filter(artist => artist.id !== id) || null;
        },
        error: (err) => {
          console.log('Error deleting artist', err);
        }
      })
  }

  onArtistUpdated(artistId: string | null | undefined, artistData: IArtistResponse) {
    const editedArtist = this.artists?.find(artist => artist.id === artistId)

    if(!editedArtist || !artistId){
      console.log('Error updating artist')
      return;
    }

    Object.assign(
      this.artists?.find(artist => artist.id === editedArtist.id) || {}, artistData
    );
  }
}
