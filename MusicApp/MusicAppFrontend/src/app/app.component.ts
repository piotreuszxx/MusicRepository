import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ArtistsComponent} from '../components/artists/artists.component';
import {SongsComponent} from '../components/songs/songs.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ArtistsComponent, SongsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'MusicAppFrontend';
}
