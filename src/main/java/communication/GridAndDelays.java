package communication;

import agent.Agent;
import com.fasterxml.jackson.databind.JsonNode;
import others.MyRandom;

import java.awt.*;
import java.util.List;

public class GridAndDelays {
	private int width_;
	private int height_;
	private int max_delay_;
	private Agent[][] grid;
	private int[][] delays;

	GridAndDelays( JsonNode gridAndDelayNode, List<Agent> agentList ) {
		this.max_delay_ = gridAndDelayNode.get( "max_delay" ).asInt();
		this.width_  = gridAndDelayNode.get( "width" ).asInt();
		this.height_ = gridAndDelayNode.get( "height" ).asInt();
		this.grid = new Agent[ width_ ][ height_ ];
		deployAllAgentsOnEnvironment( agentList );
	}

	public void deployAllAgentsOnEnvironment( List<Agent> agentList ) {
		for( Agent ag : agentList ) {
			Point p = newVacantSpot();
			grid[ ( int ) p.getY() ][ ( int ) p.getX() ] = ag;
			setAllDelays(  );
		}
	}

	private Point newVacantSpot() {
		int tempX, tempY;
		do {
			tempX = MyRandom.getRandomInt( 0, width_ - 1 );
			tempY = MyRandom.getRandomInt( 0, height_ - 1 );
		} while ( !isVacant( tempX, tempY ) );
		return new Point( tempX, tempY );
	}

	private boolean isVacant( int x, int y ) {
		return grid[ y ][ x ] == null;
	}

	private void setAllDelays( ) {
		for( int y = 0; y < height_; y++ ) {
			for( int x = 0; x < width_; x++ ) {
				Agent from = grid[y][x];
				if( from != null ) {
					setDelayFrom( from, x, y );
				}
			}
		}
	}

	private void setDelayFrom( Agent from, int fromX, int fromY ) {
		for( int toY = fromY; fromY < height_; fromY++ ) {
			for ( int toX = fromX + 1; fromX < width_; fromX++ ) {
				Agent to = grid[toY][toX];
				if( to != null ) {
					int delay = calculateDelay( fromX, fromY, toX, toY );
					delays[from.getId()][to.getId()] = delay;
					delays[to.getId()][from.getId()] = delay;
				}
			}
		}
	}

	public int getDelay( Agent from, Agent to ) {
		return delays[ from.getId() ][ to.getId() ];
	}

	/**
	 * calcurateDelayメソッド
	 * エージェント間のマンハッタン距離を計算し，returnするメソッド
	 * □□□
	 * □■□
	 * □□□
	 * このように座標を拡張し，真ん中からの距離を計算，その最短距離をとることで
	 * トーラス構造の距離関係を割り出す
	 */
	private int calculateDelay( int fromX, int fromY, int toX, int toY ) {
		int tillEnd = ( width_ + height_ ) / 2;
		int minDistance = Integer.MAX_VALUE;
		int tilesX = 3, tilesY = 3;

		for ( int i = 0; i < tilesX; i++ ) {
			int expandedX = toX + ( i - 1 ) * width_;

			for ( int j = 0; j < tilesY; j++ ) {
				int expandedY = toY + ( j - 1 ) * height_;
				int tempDistance = Math.abs( fromX - expandedX ) + Math.abs( fromY - expandedY );

				if ( tempDistance < minDistance ) {
					minDistance = tempDistance;
				}
			}
		}
		return ( int ) Math.ceil( ( double ) minDistance / tillEnd * max_delay_ );
	}

	public Agent[][] getGrid() {
		return grid;
	}

	public int[][] getDelays() {
		return delays;
	}

	public void clear() {
	}
}
